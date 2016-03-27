package techreborn.tiles.generator;

import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.common.powerSystem.TilePowerAcceptor;

import java.util.List;

/**
 * Created by modmuss50 on 25/02/2016.
 */
public class TileSolarPanel extends TilePowerAcceptor
{

	boolean shouldMakePower = false;
	boolean lastTickSate = false;

	int powerToAdd;

	public TileSolarPanel()
	{
		super(1);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (worldObj.getTotalWorldTime() % 60 == 0)
		{
			shouldMakePower = isSunOut();
		}
		if (shouldMakePower)
		{
			powerToAdd = 10;
			addEnergy(powerToAdd);
		} else
		{
			powerToAdd = 0;
		}
	}

	@Override
	public void addInfo(List<String> info, boolean isRealTile)
	{
		super.addInfo(info, isRealTile);
		if (isRealTile)
		{
			// FIXME: 25/02/2016
			// info.add(ChatFormatting.LIGHT_PURPLE + "Power gen/tick " +
			// ChatFormatting.GREEN + PowerSystem.getLocaliszedPower(
			// powerToAdd)) ;
		}
	}

	public boolean isSunOut()
	{
		return worldObj.canBlockSeeSky(pos.up()) && !worldObj.isRaining() && !worldObj.isThundering()
				&& worldObj.isDaytime();
	}

	@Override
	public double getMaxPower()
	{
		return 1000;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing direction)
	{
		return false;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing direction)
	{
		return true;
	}

	@Override
	public double getMaxOutput()
	{
		return 32;
	}

	@Override
	public double getMaxInput()
	{
		return 0;
	}

	@Override
	public EnumPowerTier getTier()
	{
		return EnumPowerTier.LOW;
	}
}
