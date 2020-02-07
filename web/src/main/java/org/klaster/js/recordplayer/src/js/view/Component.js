/**
 * Wrapped HTMLElement
 * @class
 * @property {HTMLElement} element - HTMLElement, that can be rendered in parent
 */
export class Component {

  /**
   * @constructs Component
   * @param {Object} props
   * @param {String} props.elementName - properties
   * @param {Object} props.attributes - attributes for HTMLElement
   * @param {Object} props.eventListeners - event listeners for HTMLElement
   */
  constructor(props = {}) {
    props = _.merge(Component.DEFAULT_PROPS, props);
    this._elementName = props.elementName;
    this._attributes = props.attributes;
    this._eventListeners = props.eventListeners;
    this._createElement();
    this._setAttributes();
    this._setEventHandlers();
  }

  get element() {
    return this._element;
  }

  get textContent() {
    return this.element.textContent;
  }

  set textContent(value) {
    this.element.textContent = value;
  }


  _createElement() {
    this._element = document.createElement(this._elementName);
  }

  _setAttributes() {
    if (this._attributes != null) {
      Object.keys(this._attributes).forEach(key => {
        const value = this._attributes[key];
        this._element.setAttribute(key, value);
      });
    }
  }

  _setEventHandlers() {
    if (this._eventListeners != null) {
      _.forEach(this._eventListeners, (eventListener, eventName) => {
        if (typeof eventListener == "string") {
          eventListener = this[eventListener].bind(this);
        }
        if (eventListener != null) {
          this._element.addEventListener(eventName, eventListener);
        }
      })
    }
  }

  /**
   * Appends this component as a child to node
   * @param {HTMLElement} node - parent node
   */
  appendToChildren(node) {
    node.appendChild(this._element);
  }

  /**
   * Replaces given node by self
   * @param {HTMLElement} node - replaced node
   */
  renderAt(node) {
    node.replaceWith(this._element)
  }

  /**
   * Removes this component from parent
   */
  remove() {
    this._element.parentElement.removeChild(this._element);
  }

  /**
   * Hides this component
   */
  hide() {
    this._element.hidden = true;
  }

  /**
   * Shows this component
   */
  show() {
    this._element.hidden = false;
  }

  onclick(event) {
  }

  ondrag(event) {
  }

  ondragstart(event) {
  }

  ondragover(event) {
  }

  ondrop(event) {
  }
}

Component.DEFAULT_PROPS = {
  elementName: 'div',
  attributes: {},
  eventListeners: {
    click: 'onclick',
    drag: 'ondrag',
    dragstart: 'ondragstart',
    dragover: 'ondragover',
    drop: 'ondrop'
  }
};

export default Component;