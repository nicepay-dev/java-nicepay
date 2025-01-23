package io.github.nicepay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPrint {

    private static final Logger LOGGER = LoggerFactory.getLogger("[SNAP ]");
    private static final Logger LOGGER_V2 = LoggerFactory.getLogger("[V2 ]");
    private static final Logger LOGGER_V1 = LoggerFactory.getLogger("[V1 ]");

    public void logInfoHeader(String logging) {
        LOGGER.info("\u001B[33m" + logging + "\u001B[0m");
    }

    public void logInfoBody(String logging) {
        LOGGER.info("\u001B[34m" + logging + "\u001B[0m");
    }

    public void logInfoResponse(String logging) {
        LOGGER.info("\u001B[35m" + logging + "\u001B[0m");
    }

    public void logInfo(String logging) {
        LOGGER.info("\u001B[32m" + logging + "\u001B[0m");
    }

    public void logError(String logging) {
        LOGGER.error("\u001B[31m" + logging + "\u001B[0m");
    }

    //V2
    public void logInfoBodyV2(String logging) {
        LOGGER_V2.info("\u001B[34m" + logging + "\u001B[0m");
    }

    public void logInfoResponseV2(String logging) {
        LOGGER_V2.info("\u001B[35m" + logging + "\u001B[0m");
    }

    public void logInfoV2(String logging) {
        LOGGER_V2.info("\u001B[32m" + logging + "\u001B[0m");
    }

    public void logErrorV2(String logging) {
        LOGGER_V2.error("\u001B[31m" + logging + "\u001B[0m");
    }

//    V1

    public void logInfoBodyV1(String logging) {
        LOGGER_V1.info("\u001B[34m" + logging + "\u001B[0m");
    }

    public void logInfoResponseV1(String logging) {
        LOGGER_V1.info("\u001B[35m" + logging + "\u001B[0m");
    }

    public void logInfoV1(String logging) {
        LOGGER_V1.info("\u001B[32m" + logging + "\u001B[0m");
    }

    public void logErrorV1(String logging) {
        LOGGER_V1.error("\u001B[31m" + logging + "\u001B[0m");
    }

    public static final String LOG_HEADER = "\u001B[33m {} \u001B[0m";
    public static final String LOG_BODY = "\u001B[34m {} \u001B[0m";
    public static final String LOG_RESPONSE = "\u001B[35m {} \u001B[0m";
    public static final String LOG_DEFAULT = "\u001B[32m {} \u001B[0m";
    public static final String LOG_ERROR = "\u001B[31m {} \u001B[0m";
}
