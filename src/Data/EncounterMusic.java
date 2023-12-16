package Data;

import Model.Trainer;

import java.util.ArrayList;
import java.util.Arrays;

public enum EncounterMusic {
    MALE         , // standard male encounter music
            FEMALE       , // standard female encounter music
            GIRL         , // used for male Tubers and Young Couples too
            SUSPICIOUS   ,
            INTENSE      ,
            COOL         ,
            AQUA         ,
            MAGMA        ,
            SWIMMER      ,
            TWINS        , // used for other trainer classes too
            ELITE_FOUR  ,
            HIKER       , // used for other trainer classes too
            INTERVIEWER ,
            RICH;

        public static ArrayList<String> maleClasses = new ArrayList<>(Arrays.asList(TrainerClass.PKMN_TRAINER_1.name(),
                TrainerClass.PKMN_BREEDER.name(), TrainerClass.CAMPER.name(), TrainerClass.SCHOOL_KID.name(), TrainerClass.YOUNGSTER.name(),
                TrainerClass.TRIATHLETE.name(), TrainerClass.SAILOR.name(), TrainerClass.RIVAL.name(), TrainerClass.BUG_CATCHER.name()));
        public static ArrayList<String> femaleClasses = new ArrayList<>(Arrays.asList(TrainerClass.AROMA_LADY.name(), TrainerClass.LADY.name(),
                TrainerClass.BEAUTY.name(), TrainerClass.PARASOL_LADY.name(), TrainerClass.LASS.name()));
        public static  ArrayList<String> girlClasses = new ArrayList<>(Arrays.asList(TrainerClass.TUBER_M.name(), TrainerClass.TUBER_F.name(),
                TrainerClass.PICNICKER.name(), TrainerClass.YOUNG_COUPLE.name()));
        public static  ArrayList<String> suspiciousClasses = new ArrayList<>(Arrays.asList(TrainerClass.COLLECTOR.name(), TrainerClass.HEX_MANIAC.name(),
                TrainerClass.POKEMANIAC.name(), TrainerClass.BUG_MANIAC.name(), TrainerClass.NINJA_BOY.name()));
        public static  ArrayList<String> intenseClasses = new ArrayList<>(Arrays.asList(TrainerClass.EXPERT.name(), TrainerClass.BLACK_BELT.name(),
                TrainerClass.GUITARIST.name(), TrainerClass.PSYCHIC.name(), TrainerClass.DRAGON_TAMER.name(), TrainerClass.BATTLE_GIRL.name(),
                TrainerClass.OLD_COUPLE.name()));
        public static  ArrayList<String> coolClasses = new ArrayList<>(Arrays.asList(TrainerClass.COOLTRAINER.name(), TrainerClass.BIRD_KEEPER.name(),
                TrainerClass.PKMN_RANGER.name()));
        public static  ArrayList<String> aquaClasses = new ArrayList<>(Arrays.asList(TrainerClass.TEAM_AQUA.name(), TrainerClass.AQUA_ADMIN.name(),
                TrainerClass.AQUA_LEADER.name()));
        public static  ArrayList<String> magmaClasses = new ArrayList<>(Arrays.asList(TrainerClass.TEAM_MAGMA.name(), TrainerClass.MAGMA_ADMIN.name(),
                TrainerClass.MAGMA_LEADER.name()));
        public static  ArrayList<String> swimmerClasses = new ArrayList<>(Arrays.asList(TrainerClass.SWIMMER_M.name(), TrainerClass.SWIMMER_F.name(),
                TrainerClass.SIS_AND_BRO.name()));
        public static  ArrayList<String> twinsClasses = new ArrayList<>(Arrays.asList(TrainerClass.SR_AND_JR.name(), TrainerClass.TWINS.name(),
                TrainerClass.WINSTRATE.name(), TrainerClass.POKEFAN.name()));
        public static  ArrayList<String> eliteFourClasses = new ArrayList<>(Arrays.asList(TrainerClass.ELITE_FOUR.name()));
        public static  ArrayList<String> hikerClasses = new ArrayList<>(Arrays.asList(TrainerClass.HIKER.name(), TrainerClass.RUIN_MANIAC.name(),
                TrainerClass.KINDLER.name(), TrainerClass.FISHERMAN.name()));
        public static  ArrayList<String> interviewerClasses = new ArrayList<>(Arrays.asList(TrainerClass.INTERVIEWER.name()));
        public static  ArrayList<String> richClasses = new ArrayList<>(Arrays.asList(TrainerClass.RICH_BOY.name(), TrainerClass.GENTLEMAN.name()));
}
