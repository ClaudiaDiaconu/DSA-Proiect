package org.datasource.xml;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Component
public class XMLResourceFileDataSourceConnector {

	private static final Logger logger = Logger.getLogger(XMLResourceFileDataSourceConnector.class.getName());

	@Value("${xml.data.source.file.path}")
	protected String XMLFilePath;

	protected File XMLFile;

	public File getXMLFile() throws Exception {
		logger.info("XML file path: " + this.XMLFilePath);

		if (this.XMLFile == null) {
			this.XMLFile = new File(this.XMLFilePath);

			if (!this.XMLFile.exists()) {
				logger.info("XML not found in file system. Attempting to load from classpath...");
				this.XMLFile = new File("temp.xml");
				java.nio.file.Files.copy(
						new ClassPathResource(this.XMLFilePath).getInputStream(),
						this.XMLFile.toPath(),
						StandardCopyOption.REPLACE_EXISTING
				);
				logger.info("Loaded from ClassPathResource.");
			} else {
				logger.info("Loaded from local file system.");
			}
		}

		return this.XMLFile;
	}
}

