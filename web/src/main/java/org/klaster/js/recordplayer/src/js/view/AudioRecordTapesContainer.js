import {Component} from "./Component.js";
import {AudioRecordTape} from "./AudioRecordTape.js";

/**
 * @class
 */
export class AudioRecordTapesContainer extends Component {
  constructor(props) {
    props = _.merge(AudioRecordTapesContainer.DEFAULT_PROPS, props);
    super(props);
    this._audioRecords = [];
  }

  /**
   * Adds audio record to collection
   * @param {AudioRecord} audioRecord - audio record data
   */
  addAudioRecord(audioRecord) {
    if (_.find(this._audioRecords,
        (currentAudioRecord) => currentAudioRecord.equals(audioRecord))
        == null) {
      this._audioRecords.push(audioRecord);
      const audioRecordTape = new AudioRecordTape({audioRecord: audioRecord});
      audioRecordTape.appendToChildren(this.element);
    }
  }

}

AudioRecordTapesContainer.DEFAULT_PROPS = {
  attributes: {
    class: 'dash-bordered upload-zone max-width small lightyellow'
  }
};

export default AudioRecordTapesContainer;