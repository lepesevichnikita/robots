import {Component} from "./Component.js";
import {AudioRecord} from "../model";

/**
 * Builds HTMLElement for audio record tape
 * @class
 * @property {AudioRecord} audioRecord - data about audio record
 */
export class AudioRecordTape extends Component {
  constructor(props = {}) {
    props = _.merge(AudioRecordTape.DEFAULT_PROPS, props);
    super(props);
    this._audioRecord = props.audioRecord;
    this.textContent = props.audioRecord.name;
  }

  get audioRecord() {
    return this._audioRecord;
  }

  ondragstart(event) {
    this.hide();
    event.dataTransfer.setData("text/json",
                               this._audioRecord.toJson());
  }
}

AudioRecordTape.DEFAULT_PROPS = {
  attributes: {
    class: 'icon cassette-tape narrow max-height center text',
    draggable: true
  }
};

export default AudioRecordTape;