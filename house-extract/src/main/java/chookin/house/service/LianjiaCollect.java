package chookin.house.service;

import chookin.house.SiteName;
import chookin.house.lianjia.HousePageProcessor;
import chookin.house.service.base.BaseOper;
import chookin.house.service.base.HouseCollect;
import cmri.etl.common.Request;

import java.util.Set;

/**
 * Created by zhuyin on 3/28/15.
 */
public class LianjiaCollect extends BaseOper implements HouseCollect {
    public LianjiaCollect(String[] args) {
        super(args);
    }

    @Override
    public boolean action() {
        return collectHouses();
    }

    @Override
    public String getSiteName() {
        return SiteName.Lianjia;
    }

    @Override
    public Set<Request> getSeedRequests() {
        return HousePageProcessor.getSeedRequests();
    }

    public static void main(String[] args){
        new LianjiaCollect(args).action();
    }
}
