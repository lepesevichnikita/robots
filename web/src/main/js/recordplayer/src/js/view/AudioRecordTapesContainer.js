import {Component} from "./Component.js";
import {AudioRecordTape} from "./AudioRecordTape.js";
import {AudioRecord} from "../model";

/**
 * @class
 */
export class AudioRecordTapesContainer extends Component {
  constructor(props = {}) {
    super(props);
    this._audioRecordTapes = [];
    this.ondrop = this.ondrop.bind(this);
  }

  /**
   * Adds audio record with unique name to collection
   * @param {AudioRecord} audioRecord - audio record data
   */
  addAudioRecord(audioRecord) {
    const foundAudioRecordTape = _.find(this._audioRecordTapes,
                                        (currentAudioRecordTape) => currentAudioRecordTape.audioRecord.equals(audioRecord));
    if (!foundAudioRecordTape) {
      this._createAudioRecordTape(audioRecord);
    }
  }

  prepareComponentToRender() {
    this.setEventListeners({drop: this.ondrop});
    this.setAttributes(AudioRecordTapesContainer.DEFAUL_ATTRIBUTES);
    super.prepareComponentToRender();
  }

  ondrop(event) {
    event.preventDefault();
    _.forEach(event.dataTransfer.files,
              file => {
                this._addFileAsAudioRecord(file)
              });
  }

  /**
   * Removes audio record tape with same audio record
   * @param { AudioRecord } audioRecord
   */
  removeAudioRecordTape(audioRecord) {
    const removedRecords = _.remove(this._audioRecordTapes,
                                    currentAudioRecordTape =>
                                        currentAudioRecordTape.audioRecord.equals(audioRecord));
    removedRecords.forEach(removedAudioRecordTape => {
      removedAudioRecordTape.removeFromParent();
    });
  }

  /**
   * Creates AudioRecordTape based on audio record
   * @param {AudioRecord} audioRecord - source audio record
   * @private
   */
  _createAudioRecordTape(audioRecord) {
    const audioRecordTape = new AudioRecordTape({audioRecord});
    this._audioRecordTapes.push(audioRecordTape);
    audioRecordTape.appendToChildren(this.element);
  }

  /**
   * Adds file as audio record to container if it is audio file
   * @param {File} file - file for adding to container
   * @private
   */
  _addFileAsAudioRecord(file) {
    const typeFormat = 'audio';
    if (file.type.indexOf(typeFormat) > -1) {
      const newAudioRecord = new AudioRecord({
                                               name: file.name,
                                               path: URL.createObjectURL(file)
                                             });
      this.addAudioRecord(newAudioRecord);
    }
  }
}

AudioRecordTapesContainer.DEFAUL_ATTRIBUTES = {
  class: 'dash-bordered upload-zone max-width small lightyellow'
};

export default AudioRecordTapesContainer;