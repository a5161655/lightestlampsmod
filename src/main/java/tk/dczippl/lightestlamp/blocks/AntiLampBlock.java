package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tk.dczippl.lightestlamp.init.ModBlocks;
import tk.dczippl.lightestlamp.tile.AlfaLampTileEntity;
import tk.dczippl.lightestlamp.tile.AntiLampTileEntity;

import javax.annotation.Nullable;
import java.util.List;

public class AntiLampBlock extends Block
{
    public AntiLampBlock()
    {
        super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(0.3f,1));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 10;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new AntiLampTileEntity();
    }

    @Override
    public int getHarvestLevel(BlockState p_getHarvestLevel_1_)
    {
        return 0;
    }

    @Override
    public void onPlayerDestroy(IWorld iworld, BlockPos pos, BlockState state)
    {
        World world = iworld.getWorld();
        BlockPos.getAllInBox(pos.offset(Direction.UP, 1).offset(Direction.NORTH,1).offset(Direction.WEST,1), pos.offset(Direction.DOWN, 1).offset(Direction.SOUTH,1).offset(Direction.EAST,1)).forEach((pos1) -> {
            if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR.get())
            {
                world.setBlockState(pos1, Blocks.AIR.getDefaultState());
            }
        });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> text, ITooltipFlag flag)
    {
        text.add(new TranslationTextComponent("tooltip.lightestlamp.type.antimatter").func_240699_a_(TextFormatting.GRAY));
        text.add(new TranslationTextComponent("tooltip.lightestlamp.lamp.wip").func_240699_a_(TextFormatting.RED));
    }
}
