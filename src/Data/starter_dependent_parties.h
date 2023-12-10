static const struct TrainerMon sParty_MorganFisheyeSet0Choice0[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.iv = TRAINER_PARTY_IVS(10, 10, 10, 10, 10, 10),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_NONE, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice0Hard[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.heldItem = ITEM_ORAN_BERRY,
	.iv = TRAINER_PARTY_IVS(15, 15, 15, 15, 15, 15),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_FLASH, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice0Unfair[] = {
	{
	.species = SPECIES_ELADRIFT,
	.lvl = 6,
	.heldItem = ITEM_BERRY_JUICE,
	.iv = TRAINER_PARTY_IVS(20, 20, 20, 20, 20, 20),
	.moves = { MOVE_TACKLE, MOVE_GROWL, MOVE_WATER_GUN, MOVE_FLASH, },
	.nature = TRAINER_PARTY_NATURE(NATURE_NAUGHTY),
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice1[] = {
	{
	.species = SPECIES_GLASMA,
	.lvl = 6,
	.iv = TRAINER_PARTY_IVS(10, 10, 10, 10, 10, 10),
	.moves = { MOVE_TACKLE, MOVE_LEER, MOVE_THUNDER_SHOCK, MOVE_NONE, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice1Hard[] = {
	{
	.species = SPECIES_GLASMA,
	.lvl = 6,
	.heldItem = ITEM_ORAN_BERRY,
	.iv = TRAINER_PARTY_IVS(15, 15, 15, 15, 15, 15),
	.moves = { MOVE_TACKLE, MOVE_LEER, MOVE_THUNDER_SHOCK, MOVE_FLASH, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice1Unfair[] = {
	{
	.species = SPECIES_GLASMA,
	.lvl = 6,
	.heldItem = ITEM_BERRY_JUICE,
	.iv = TRAINER_PARTY_IVS(20, 20, 20, 20, 20, 20),
	.moves = { MOVE_TACKLE, MOVE_LEER, MOVE_THUNDER_SHOCK, MOVE_FLASH, },
	.nature = TRAINER_PARTY_NATURE(NATURE_MILD),
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice2[] = {
	{
	.species = SPECIES_SANDUDE,
	.lvl = 6,
	.iv = TRAINER_PARTY_IVS(10, 10, 10, 10, 10, 10),
	.moves = { MOVE_TACKLE, MOVE_HARDEN, MOVE_MUD_SLAP, MOVE_NONE, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice2Hard[] = {
	{
	.species = SPECIES_SANDUDE,
	.lvl = 6,
	.heldItem = ITEM_ORAN_BERRY,
	.iv = TRAINER_PARTY_IVS(15, 15, 15, 15, 15, 15),
	.moves = { MOVE_TACKLE, MOVE_HARDEN, MOVE_MUD_SLAP, MOVE_FLASH, },
	},
};

static const struct TrainerMon sParty_MorganFisheyeSet0Choice2Unfair[] = {
	{
	.species = SPECIES_SANDUDE,
	.lvl = 6,
	.heldItem = ITEM_BERRY_JUICE,
	.iv = TRAINER_PARTY_IVS(20, 20, 20, 20, 20, 20),
	.moves = { MOVE_TACKLE, MOVE_HARDEN, MOVE_SAND_TOMB, MOVE_FLASH, },
	.nature = TRAINER_PARTY_NATURE(NATURE_IMPISH),
	},
};

static const struct TrainerMon * const sStarterDependentParties[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {
	[STARTER_MON_0] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice0,
	},
	[STARTER_MON_1] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice1,
	},
	[STARTER_MON_2] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice2,
	},
};

static const struct TrainerMon * const sStarterDependentPartiesHard[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {
	[STARTER_MON_0] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice0Hard,
	},
	[STARTER_MON_1] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice1Hard,
	},
	[STARTER_MON_2] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice2Hard,
	},
};

static const struct TrainerMon * const sStarterDependentPartiesUnfair[STARTER_MON_COUNT][STARTER_DEPENDENT_PARTIES_COUNT] = {
	[STARTER_MON_0] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice0Unfair,
	},
	[STARTER_MON_1] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice1Unfair,
	},
	[STARTER_MON_2] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = sParty_MorganFisheyeSet0Choice2Unfair,
	},
};

static const u8 sStarterDependentPartySizes[3][STARTER_DEPENDENT_PARTIES_COUNT] = {
	[OPTIONS_DIFFICULTY_NORMAL] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_MorganFisheyeSet0Choice0),
	},
	[OPTIONS_DIFFICULTY_HARD] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_MorganFisheyeSet0Choice0Hard),
	},
	[OPTIONS_DIFFICULTY_UNFAIR] = {
		[TRAINER_MORGAN_FISHEYE_LAKE - FIRST_STARTER_DEPENDENT_INDEX] = ARRAY_COUNT(sParty_MorganFisheyeSet0Choice0Unfair),
	},
};

