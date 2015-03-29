package chookin.house.service;

import chookin.house.service.base.BaseOper;

/**
 * Created by zhuyin on 3/28/15.
 */
public class TagCli extends BaseOper {
    public TagCli(String[] args) {
        super(args);
    }

    @Override
    public boolean action() {
        if(getOptionParser().existOption("dump-config")){
            return new ConfigProcess(getArgs()).action();
        }
        return false;
    }

    public static void main(String[] args){
        new TagCli(args).action();
    }
}
