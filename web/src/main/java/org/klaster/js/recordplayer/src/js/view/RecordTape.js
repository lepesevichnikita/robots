import {View} from "./View.js";

export class RecordTape extends View {
  constructor(props) {
    super(props);
    this._audioRecord = props.audioRecord;
  }

  render() {
    const item = document.createElement("div");
    item.setAttribute('id', `${this._audioRecord.name}`);
    item.setAttribute('class', 'icon cassette-tape narrow max-height center');
    return item;
  }

}