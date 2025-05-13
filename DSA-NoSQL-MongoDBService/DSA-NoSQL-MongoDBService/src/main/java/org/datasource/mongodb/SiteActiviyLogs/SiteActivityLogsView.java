package org.datasource.mongodb.SiteActiviyLogs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties({"_id"})
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SiteActivityLogsView implements Serializable {
    private String userId;
    private String action;
    private Date timestamp;
    private String ipAddress;
    private Integer oracleId;
}
