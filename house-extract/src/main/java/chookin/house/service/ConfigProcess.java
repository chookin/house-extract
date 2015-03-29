package chookin.house.service;

import chookin.house.service.base.BaseOper;
import cmri.utils.configuration.ConfigManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by zhuyin on 3/29/15.
 */
public class ConfigProcess extends BaseOper {
    private static Logger LOG= Logger.getLogger(ConfigProcess.class);
    public ConfigProcess(String[] args) {
        super(args);
    }

    @Override
    public boolean action() {
        try {
            return dumpConfigFile();
        } catch (IOException e) {
            LOG.error(null, e);
            return true;
        }
    }

    boolean dumpConfigFile() throws IOException {
        ConfigManager.dump();
        return true;
    }
}
