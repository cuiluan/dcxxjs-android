package com.dcxxjs.core;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.config.DbConfigs;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

import java.io.File;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/24
 * 功能描述：数据库管理类
 * 联系方式：
 */
public class DBMangerCore implements DbManager.DbUpgradeListener, DbManager.TableCreateListener {

    private static DBMangerCore dbMangerCore;

    private DbManager.DaoConfig daoConfig;
    private static DbManager dbManager;

    public static DbManager getInstance(int ver,String dbname,String path) {
        if (null == dbMangerCore) {
            dbMangerCore = new DBMangerCore(ver,dbname,path);
        }
        return dbManager;
    }

    protected  DBMangerCore(int ver,String dbname,String path) {
        File file;
        daoConfig = new DbManager.DaoConfig()
                //设置数据库的版本号
                .setDbVersion(ver)
                //设置数据库名，默认xutils.db
                .setDbName(dbname)
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能，对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(this)
                //设置表创建的监听
                .setTableCreateListener(this);
        //设置数据库路径，默认存储在app的私有目录
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(path);
            daoConfig.setDbDir(file);
        }
        dbManager = x.getDb(daoConfig);
    }

    /**
     * 数据库更新
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

    }

    /**
     * 表创建更新
     *
     * @param db
     * @param table
     */
    @Override
    public void onTableCreated(DbManager db, TableEntity<?> table) {

    }
}
