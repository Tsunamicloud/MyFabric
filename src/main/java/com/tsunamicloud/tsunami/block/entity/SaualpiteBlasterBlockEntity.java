package com.tsunamicloud.tsunami.block.entity;

import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.item.inventory.ImplementedInventory;
import com.tsunamicloud.tsunami.recipe.SaualpiteBlasterRecipe;
import com.tsunamicloud.tsunami.screen.SaualpiteBlasterScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class SaualpiteBlasterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //新建inventory来存储gui中的东西
    //4个slot：2个input，1个fuel，1个output
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);


    //PropertyDelegate负责将field在server和client之间sync
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;//烧制（crafting）的进程，以数字表征
    private int maxProgress = 72;
    private int fuelTime = 0;//燃料的剩余燃烧时间
    private int maxFuelTime = 0;


    
    public SaualpiteBlasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SAUALPITE_BLASTER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return SaualpiteBlasterBlockEntity.this.progress;
                    case 1: return SaualpiteBlasterBlockEntity.this.maxProgress;
                    case 2: return SaualpiteBlasterBlockEntity.this.fuelTime;
                    case 3: return SaualpiteBlasterBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }
            public void set(int index, int value) {
                switch(index) {
                    case 0: SaualpiteBlasterBlockEntity.this.progress = value; break;
                    case 1: SaualpiteBlasterBlockEntity.this.maxProgress = value; break;
                    case 2: SaualpiteBlasterBlockEntity.this.fuelTime = value; break;
                    case 3: SaualpiteBlasterBlockEntity.this.maxFuelTime = value; break;
                }
            }
            public int size() {
                return 4;
            }
        };
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public Text getDisplayName() {
        return new LiteralText("Saualpite Blaster");
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SaualpiteBlasterScreenHandler(syncId, inv, this, this.propertyDelegate);
    }


    //读写nbt:进入和退出世界时，保存数据
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("blaster.progress", progress);
        nbt.putInt("blaster.fuelTime", fuelTime);
        nbt.putInt("blaster.maxFuelTime", maxFuelTime);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("blaster.progress");
        fuelTime = nbt.getInt("blaster.fuelTime");
        maxFuelTime = nbt.getInt("blaster.maxFuelTime");
    }



    private void consumeFuel() {
        if(!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, SaualpiteBlasterBlockEntity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe(entity)) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(SaualpiteBlasterBlockEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(SaualpiteBlasterBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(SaualpiteBlasterBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SaualpiteBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(SaualpiteBlasterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(SaualpiteBlasterBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<SaualpiteBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(SaualpiteBlasterRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(1,1);
            entity.removeStack(2,1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }


}










//不添加自定义配方的代码版本

/*
public class SaualpiteBlasterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //新建inventory来存储gui中的东西
    //4个slot：2个input，1个fuel，1个output
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    public SaualpiteBlasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SAUALPITE_BLASTER, pos, state);
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public Text getDisplayName() {
        return new LiteralText("Saualpite Blaster");
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SaualpiteBlasterScreenHandler(syncId, inv, this);
    }


    //读写nbt:进入和退出世界时，保存数据
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }



    //每个tick都会被调用
    public static void tick(World world, BlockPos pos, BlockState state, SaualpiteBlasterBlockEntity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    private static void craftItem(SaualpiteBlasterBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);

        entity.setStack(3, new ItemStack(ModItems.SAUALPITE_PICKAXE,
                entity.getStack(3).getCount() + 1));
    }

    private static boolean hasRecipe(SaualpiteBlasterBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.LILAC_FLOWER_BULB;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.GOLDEN_PICKAXE;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == ModItems.SAUALPITE;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
    }

    private static boolean hasNotReachedStackLimit(SaualpiteBlasterBlockEntity entity) {
        //slot从0开始计数
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }


}

 */
