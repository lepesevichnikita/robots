import {Component} from "./Component.js";
import {AudioRecordTape} from "./AudioRecordTape.js";
import {AudioRecord} from "../model";

/**
 * @class
 */
export class AudioRecordTapesContainer extends Component {
  constructor(props = {}) {
    props = _.merge(AudioRecordTapesContainer.DEFAULT_PROPS, props);
    super(props);
    this._audioRecords = [];
    this._audioRecordTapes = [];
  }

  /**
   * Adds audio record with unique name to collection
   * @param {AudioRecord} audioRecord - audio record data
   */
  addAudioRecord(audioRecord) {
    if (_.find(this._audioRecords,
        (currentAudioRecord) => currentAudioRecord.equals(audioRecord))
        == null) {
      this._audioRecords.push(audioRecord);
      this._createAudioRecordTapeAndAddToChildren(audioRecord);
    }
  }

  /**
   * Creates AudioRecordTape based on audio record
   * @param {AudioRecord} audioRecord - source audio record
   * @private
   */
  _createAudioRecordTapeAndAddToChildren(audioRecord) {
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

  removeAudioRecordTape(audioRecord) {
    const removedRecords = _.remove(this._audioRecords,
                                    currentAudioRecord => currentAudioRecord.equals(audioRecord));
    _.forEach(removedRecords,
              removedAudioRecord => {
                _.remove(this._audioRecordTapes,
                         audioRecordTape => audioRecordTape.audioRecord.equals(removedAudioRecord))
                 .forEach(removedAudioRecordTape => removedAudioRecordTape.remove());
              });
  }

  ondrop(event) {
    event.preventDefault();
    _.forEach(event.dataTransfer.files,
              file => {
                this._addFileAsAudioRecord(file)
              });
  }
}

AudioRecordTapesContainer.DEFAULT_PROPS = {
  attributes: {
    class: 'dash-bordered upload-zone max-width small lightyellow'
  }
};

export default AudioRecordTapesContainer;