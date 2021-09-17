//This Class is responsible for processing the AppCenter API Json to get the APK Download URL
package utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class JACProcessor {
    public  String download_url;

    public JACProcessor(@JsonProperty("download_url") String download_url)
    {

        this.download_url = download_url;
    }

}
