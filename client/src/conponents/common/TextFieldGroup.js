import React from 'react'
import classnames from 'classnames';
import PropTypes from 'prop-types';

const TextFieldGroup = ({
    name,
    placeholder,
    value,
    label,
    error,
    info,
    type,
    onChange,
    disable
}) => {
  return (
    <div className="form-group">
        {/* using classname allows to add logic to the inputs if invalid */}
        <input 
            type={type} 
            className={classnames('form-control form-control-lg',{
            'is-invalid': error
            })} 
            placeholder={placeholder}
            name={name} 
            value={value} 
            onChange={onChange}
            disabled={disable}
            />
            {/* this shows the error message */}
            {info && <small className="form-text text-muted">{info}</small>}
            {error && (<div className="invalid-feedback">{error}</div>)}
    </div>

  )
}
TextFieldGroup.propTypes = {
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string,
  value: PropTypes.string.isRequired,
  info: PropTypes.string,
  error: PropTypes.string,
  type: PropTypes.string.isRequired,
  onChange: PropTypes.funcisRequired,
  disable: PropTypes.string
};

TextFieldGroup.defaultProps = {
    type: 'text'
}

export default TextFieldGroup;