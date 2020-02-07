import {AudioRecordTapesContainer} from "./view";
import {AudioRecord} from "./model";
import {preventDefaultDragoverAndDrop} from "./service";

preventDefaultDragoverAndDrop();

const uploadZone = document.getElementById("upload-zone");

const audioRecordTapesContainer = new AudioRecordTapesContainer({
  attributes: {id: 'upload-zone'},
  eventListeners: {
    drop: (e) => {
      e.preventDefault();
      console.dir(e);
      _.forEach(e.dataTransfer.files, file => addAudioFileToContainer(file));
    }
  }
});

audioRecordTapesContainer.renderAt(uploadZone);

/**
 * Adds file to container if it is an audio
 * @param {File} file - file for adding
 */
const addAudioFileToContainer = (file) => {
  if (file.type.indexOf('audio') > -1) {
    const audioRecord = new AudioRecord({
      name: file.name,
      path: URL.createObjectURL(file)
    });
    audioRecordTapesContainer.addAudioRecord(audioRecord);
  }
};

