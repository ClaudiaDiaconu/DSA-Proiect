package org.datasource.xml.locations;

import org.datasource.xml.XMLResourceFileDataSourceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PropertyHistoryViewBuilder {

    private static Logger logger = Logger.getLogger(PropertyHistoryViewBuilder.class.getName());

    private PropertyHistoryListView propertyHistoryListView;
    private List<org.datasource.xml.locations.PropertyHistoryView> entries;

    private XMLResourceFileDataSourceConnector dataSourceConnector;
    private File xmlFile;

    @Autowired
    public PropertyHistoryViewBuilder(XMLResourceFileDataSourceConnector dataSourceConnector) throws Exception {
        this.dataSourceConnector = dataSourceConnector;
        this.xmlFile = dataSourceConnector.getXMLFile();
    }

    public List<org.datasource.xml.locations.PropertyHistoryView> getEntries() {
        return entries;
    }

    public PropertyHistoryListView getPropertyHistoryListView() {
        return propertyHistoryListView;
    }

    public PropertyHistoryViewBuilder build() throws Exception {
        return this.select();
    }

    private PropertyHistoryViewBuilder select() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(PropertyHistoryListView.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.propertyHistoryListView = (PropertyHistoryListView) jaxbUnmarshaller.unmarshal(xmlFile);
        this.entries = this.propertyHistoryListView.getEntries();
        logger.info("Loaded XML: " + entries.size() + " records");
        return this;
    }
}
