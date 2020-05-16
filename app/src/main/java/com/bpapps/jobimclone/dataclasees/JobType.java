package com.bpapps.jobimclone.dataclasees;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        JobType.NOT_DEFINED,
        JobType.REAL_ESTATE_AGENT, JobType.DRIVER_HELPER, JobType.PRODUCTION_WORKER_GATHERER, JobType.BARMEN,
        JobType.COUNTER_WORKER, JobType.HOST, JobType.DELIVERER, JobType.CLEANER, JobType.MAINTENANCE_MAN, JobType.SELLES_SERV_REP, JobType.SELLER, JobType.COOK,
        JobType.SECURITY_GUARD, JobType.KITCHEN_WORKER, JobType.PETROL_STATION_WORKER, JobType.BRANCH_MANAGER,
        JobType.TEENAGERS, JobType.WORK_FROM_HOME, JobType.HAIR_DRESSER, JobType.DESK_WORKER,
        JobType.GARDENER, JobType.KINDERGARTEN, JobType.DRIVER, JobType.HEAD_OF_SHIFT,
        JobType.SELLES_ATTENDANT, JobType.CASHIER, JobType.USHER, JobType.STOREKEEPER, JobType.WAITER})

public @interface JobType {
    int NOT_DEFINED = -1;
    int REAL_ESTATE_AGENT = 0;
    int DRIVER_HELPER = 1;
    int PRODUCTION_WORKER_GATHERER = 2;
    int BARMEN = 3;
    int COUNTER_WORKER = 4;
    int HOST = 5;
    int DELIVERER = 6;
    int CLEANER = 7;
    int MAINTENANCE_MAN = 8;
    int SELLES_SERV_REP = 9;
    int SELLER = 10;
    int COOK = 11;
    int SECURITY_GUARD = 12;
    int KITCHEN_WORKER = 13;
    int PETROL_STATION_WORKER = 14;
    int BRANCH_MANAGER = 15;
    int TEENAGERS = 16;
    int WORK_FROM_HOME = 17;
    int HAIR_DRESSER = 18;
    int DESK_WORKER = 19;
    int GARDENER = 20;
    int KINDERGARTEN = 21;
    int DRIVER = 22;
    int HEAD_OF_SHIFT = 23;
    int SELLES_ATTENDANT = 24;
    int CASHIER = 25;
    int USHER = 26;
    int STOREKEEPER = 27;
    int WAITER = 28;
}

