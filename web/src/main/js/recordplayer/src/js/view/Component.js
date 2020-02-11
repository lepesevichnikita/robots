/**
 * Wrapped HTMLElement
 * @class
 * @property {HTMLElement} element - HTMLElement, that can be rendered in parent
 *
 */
export class Component {

  /**
   * @constructs Component
   * @param {Object} props
   * @param {string} props.elementName - properties
   * @param {Object} props.attributes - attributes for HTMLElement
   * @param {Object} props.eventListeners - event listeners for HTMLElement
   */
  constructor(props = {}) {
    props = _.merge(Component.DEFAULT_PROPS,
                    props);
    this._elementName = props.elementName;
    this._attributes = props.attributes;
    this._eventListeners = props.eventListeners;
  }

  /**
   * Sets concrete attribute
   * @param { object } attributes - attributes for component
   */
  setAttributes(attributes) {
    this._attributes = _.clone(this._attributes);
    _.merge(this._attributes,
            attributes);
  }

  /**
   * Sets concrete event listener
   * @param { Object } eventListeners - event listeners for component
   */
  setEventListeners(eventListeners) {
    this._eventListeners = _.clone(this._eventListeners);
    _.merge(this._eventListeners,
            eventListeners);
  }

  get element() {
    return this._element;
  }

  /**
   * Appends this component as a child to node
   * @param {HTMLElement} node - parent node
   */
  appendToChildren(node) {
    node.appendChild(this.render());
  }

  /**
   * Replaces given node by self
   * @param {HTMLElement} node - replaced node
   */
  renderAt(node) {
    node.replaceWith(this.render())
  }

  /**
   * Removes this component from parent
   */
  removeFromParent() {
    this._element.parentElement.removeChild(this._element);
  }

  /**
   * Initializes new instance of HTMLElement and returns it
   * @returns { HTMLElement}
   */
  render() {
    this._initializeElement();
    return this._element;
  }

  rerender() {
    const oldElement = this._element;
    if (oldElement) {
      oldElement.parentElement.replaceChild(this.render(),
                                            oldElement);
    }
  }

  hide() {
    this._element.hidden = true;
  }

  show() {
    this._element.hidden = false;
  }

  _initializeElement() {
    this._createElement();
    this._setAttributes();
    this._setEventHandlers();
  }

  _createElement() {
    this._element = document.createElement(this._elementName);
  }

  _setAttributes() {
    _.forEach(this._attributes,
              (attributeValue, attributeName) => {
                this._element.setAttribute(attributeName,
                                           attributeValue);
              });
  }

  _setEventHandlers() {
    _.forEach(this._eventListeners,
              (eventListener, eventName) => {
                this._element.addEventListener(eventName,
                                               eventListener);
              })
  }
}

Component.DEFAULT_PROPS = {
  elementName: 'div',
  attributes: {},
  eventListeners: {}
};

export default Component;