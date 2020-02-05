import {RecordTape} from "./view";
import {AudioRecord} from "./model";

window.addEventListener("dragover", function (e) {
  e = e || event;
  e.preventDefault();
}, false);
window.addEventListener("drop", function (e) {
  e = e || event;
  e.preventDefault();
}, false);
const input = document.getElementById("file-uploader");
const uploadZone = document.getElementById("upload-zone");
uploadZone.addEventListener("drop", (e) => {
  e.preventDefault();
  e.stopPropagation();
  console.dir(e);
});
const child = new RecordTape({
  audioRecord: new AudioRecord({name: 'test', path: 'undefined'})
});
uploadZone.appendChild(child.render());
