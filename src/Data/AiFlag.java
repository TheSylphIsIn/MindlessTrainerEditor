package Data;

public enum AiFlag {
    CHECK_BAD_MOVE,
TRY_TO_FAINT,
CHECK_VIABILITY ,
SETUP_FIRST_TURN,
RISKY,
PREFER_STRONGEST_MOVE ,
PREFER_BATON_PASS,
HP_AWARE,
// New, Trainer Handicap Flags
NEGATE_UNAWARE ,   // AI is NOT aware of negating effects like wonder room, mold breaker, etc
WILL_SUICIDE,  // AI will use explosion / self destruct / final gambit / etc
// New, Trainer Strategy Flags
HELP_PARTNER ,  // AI can try to help partner. If not set, will tend not to target partner
PREFER_STATUS_MOVES,  // AI gets a score bonus for status moves. Should be combined with AI_FLAG_CHECK_BAD_MOVE to prevent using only status moves
STALL,  // AI stalls battle and prefers secondary damage/trapping/etc. TODO not finished
SCREENER,  // AI prefers screening effects like reflect, mist, etc. TODO unfinished
SMART_SWITCHING,  // AI includes a lot more switching checks
ACE_POKEMON,  // AI has an Ace Pokemon. The last Pokemon in the party will not be used until it's the last one remaining.
OMNISCIENT // AI has full knowledge of player moves, abilities, hold items
}
