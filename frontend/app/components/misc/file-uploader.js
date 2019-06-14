import FileField from "ember-uploader/components/file-field";
import Uploader from "ember-uploader/uploaders/uploader";
import { isEmpty } from "@ember/utils";

export default FileField.extend({
  filesDidChange(files) {
    const uploader = Uploader.create({
      url: this.get("url")
    });

    if (!isEmpty(files)) {
      // this second argument is optional and can to be sent as extra data with the upload
      uploader.upload(files[0], {
        restaurantId: this.get("restaurantId"),
        imageType: this.get("imageFor")
      });
    }

    uploader.on("progress", e => {
      this.set("progress", Math.round(e.percent) - 1);
    });

    uploader.on("didUpload", ({ path }) => {
      this.set("progress", null);
      this.sendAction("onFinishedUpload", path);
    });
  }
});
