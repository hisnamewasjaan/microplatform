package microplatform.adservice.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Image {

    private byte[] data;

    public static Image of(byte[] data) {
        return new Image(data);
    }

    public byte[] getData() {
        return data;
    }

    public Image() {
    }

    private Image(byte[] data) {
        this.data = data;
    }

}
