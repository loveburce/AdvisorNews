package com.micky.commonproj.domain.db.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.db.manager
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 13:33
 * @Version 1.0
 */
public class GreenDaoGenerator {

    /**需要生成model的时候，请设置成true, 用于防止手贱点错了 */
    public static final boolean GENERATE_MODEL = false;

    /**
     * 数据库版本
     * 如果数据库需要升级,请在DaoMaster中的onUpgrade方法中加入:
     * MigrationHelper.getInstance().migrate(db, IpInfoDao.class, PlaceDao.class，...);
     */
    public static final int DB_VERSION = 1;

    public static void main(String[] args) throws Exception {
        if (GENERATE_MODEL) {
            Schema schema = new Schema(DB_VERSION, "com.micky.commonproj.domain.model");
            schema.setDefaultJavaPackageDao("com.micky.commonproj.domain.db.dao");
            schema.enableKeepSectionsByDefault();
            addUser(schema);
            addPlace(schema);
            addChannelItem(schema);
            new DaoGenerator().generateAll(schema, "./domain/src/main/java-gen");
        }
    }

    private static void addUser(Schema schema) {
        Entity user = schema.addEntity("IpInfo");
        user.addIdProperty().primaryKey();
        user.addStringProperty("country");
        user.addDoubleProperty("country_id");
        user.addDoubleProperty("area");
        user.addDoubleProperty("area_id");
        user.addDoubleProperty("ip");
    }

    private static void addPlace(Schema schema) {
        Entity card = schema.addEntity("Place");
        card.addIdProperty().primaryKey();
        card.addStringProperty("label");
        card.addStringProperty("name");
        card.addStringProperty("pinyin");
        card.addStringProperty("province");
    }

    private static void addChannelItem(Schema schema) {
        Entity card = schema.addEntity("ChannelItem");
        card.addIdProperty().primaryKey();
        card.addIntProperty("cId");
        card.addStringProperty("name");
        card.addIntProperty("orderId");
        card.addIntProperty("selected");
        card.addStringProperty("urlHead");
        card.addStringProperty("urlKey");
        card.addStringProperty("urlEnd");
    }
}
