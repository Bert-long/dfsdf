package neusoft.transfer;

import com.neusoft.ui.bean.TableListDtoInner;
import com.neusoft.ui.bean.UrlListDtoInner;
import lombok.Data;

@Data
public class UrlTraffer {
    public UrlTraffer(Object[] sqlObjects) {
        int index = 0;
        this.id = (String) sqlObjects[index++];
        this.url = (String) sqlObjects[index++];
    }

    public UrlTraffer(String id, String url) {
        this.id = id;
        this.url = url;
    }

    private String id;
    private String url;

    public UrlListDtoInner toDto() {
        UrlListDtoInner urlListDtoInner = new UrlListDtoInner();
        urlListDtoInner.setId(id);
        urlListDtoInner.setUrl(url);
        return urlListDtoInner;
    }
}
