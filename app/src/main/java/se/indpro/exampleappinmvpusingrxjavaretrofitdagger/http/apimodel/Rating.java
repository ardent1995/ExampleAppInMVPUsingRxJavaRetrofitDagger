
package se.indpro.exampleappinmvpusingrxjavaretrofitdagger.http.apimodel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Rating {

    @SerializedName("Source")
    private String mSource;
    @SerializedName("Value")
    private String mValue;

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
