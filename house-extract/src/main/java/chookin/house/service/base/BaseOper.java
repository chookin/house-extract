package chookin.house.service.base;

import cmri.etl.common.NetworkHelper;
import cmri.utils.configuration.ConfigManager;
import cmri.utils.lang.DateHelper;
import cmri.utils.lang.OptionParser;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by zhuyin on 3/28/15.
 */
public abstract class BaseOper {
    private static Logger LOG;
    private String[] args;
    private OptionParser optionParser;

    static {
        // configure log4j to log to custom file at runtime. In the java program directly by setting a system property (BEFORE you make any calls to log4j).
        try {
            String actionName = System.getProperty("action");
            if(actionName ==null) {
                System.setProperty("host.name.time", InetAddress.getLocalHost().getHostName() + "-" + DateHelper.convertToDateString(new Date(), "yyyyMMddHHmmss"));
            }else{
                System.setProperty("host.name.time", actionName + "-" + InetAddress.getLocalHost().getHostName() + "-" + DateHelper.convertToDateString(new Date(), "yyyyMMddHHmmss"));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        LOG = Logger.getLogger(BaseOper.class);
        ConfigManager.setFile("house-extract.xml");
        NetworkHelper.setDefaultProxy();
    }

    public BaseOper(String[] args) {
        if (args.length == 0) {
            String defaultArgs = ConfigManager.getProperty("cli.paras");
            if (defaultArgs != null) {
                this.args = defaultArgs.split(" ");
            }
        } else {
            this.args = args;
        }
        LOG.info("args: " + Arrays.toString(this.args));
        optionParser = new OptionParser(this.args);
    }

    public String[] getArgs(){
        return args;
    }
    public OptionParser getOptionParser(){
        return optionParser;
    }

    /**
     * @return true if execute.
     */
    public abstract boolean action();
}