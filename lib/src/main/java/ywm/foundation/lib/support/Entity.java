package ywm.foundation.lib.support;

import com.wolf.jpa.model.JpaEntity;
import com.wolf.mongo.model.MongoEntity;

/**
 * Created by Herbert Yu on 2019-11-12 18:24
 */
public interface Entity {


    abstract class Mongo extends MongoEntity {
        /**
         * 更新时间
         */
        private long updateTime = System.currentTimeMillis();

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }

    abstract class Jpa extends JpaEntity {
        /**
         * 更新时间
         */
        private long updateTime = System.currentTimeMillis();

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

    }
}
