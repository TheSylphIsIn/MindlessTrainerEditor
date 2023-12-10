static const struct TrainerMon sParty_MorganFisheye[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.iv = TRAINER_PARTY_IVS(10, 10, 10, 10, 10, 10),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_NONE, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeHard[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.heldItem = ITEM_ORAN_BERRY,
	.iv = TRAINER_PARTY_IVS(15, 15, 15, 15, 15, 15),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_FLASH, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeUnfair[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.heldItem = ITEM_BERRY_JUICE,
	.iv = TRAINER_PARTY_IVS(20, 20, 20, 20, 20, 20),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_FLASH, },
	.nature = TRAINER_PARTY_NATURE(NATURE_NAUGHTY),
	},
};

