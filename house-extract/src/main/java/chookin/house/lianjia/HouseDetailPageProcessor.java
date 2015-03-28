package chookin.house.lianjia;

import chookin.house.House;
import cmri.etl.common.ResultItems;
import cmri.etl.processor.PageProcessor;
import cmri.utils.lang.StringHelper;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyin on 3/28/15.
 */
public class HouseDetailPageProcessor implements PageProcessor {
    private static final Logger LOG = Logger.getLogger(HouseDetailPageProcessor.class);
    private static HouseDetailPageProcessor processor = new HouseDetailPageProcessor();
    public static HouseDetailPageProcessor getInstance(){
        return processor;
    }
    private HouseDetailPageProcessor(){}
    @Override
    public void process(ResultItems page) {
        House house = page.getRequest().getExtra("house", House.class);
        Document doc = (Document) page.getResource();

        Element element = doc.select(".zone-name").first();
        house.set("street", element.text());//小区

        element = doc.select(".region").first();//西城区...
        if(element != null){
            house.set("region", element.text());
        }

        String text = doc.text();

        house.set("floor", StringHelper.parseRegex(text, "楼层：([\\w]+楼)", 1));

        Elements elements = doc.select(".items.clear .content");
        List<String> comments = new ArrayList<>();
        for(Element item : elements){
            String comment = item.text();
            comments.add(comment);
        }
        if(!comments.isEmpty()){
            house.set("comments", comments);
        }
        LOG.trace(house);
        page.setField("house", house);
    }
}
