import React, { Component } from "react";
import PropTypes from "prop-types";
import isEmpty from "../../validation/is-empty";
import Spinner from "../common/Spinner";

class ProfileAbout extends Component {
  render() {
    const { profile } = this.props;
    let AboutContent;
    // Skill List
    // const skills = profile.skills.map((skill, index) => (
    //   <div key={index} className="p-3">
    //     <i className="fa fa-check" /> {skill}
    //   </div>
    // ));

    if (profile.user == null) {
        AboutContent = <Spinner />;
    } else {
        // Get first name
        const firstName = profile.user.name.trim().split(" ")[0];
        AboutContent = (
            <div className="card card-body text-center bg-light mb-3">
                <h3 className="text-center text-info">{firstName}'s Bio</h3>
                <p className="lead">
                    {isEmpty(profile.bio) ? (
                        <span>{firstName} does not have a bio</span>
                    ) : (
                        <span>{profile.bio}</span>
                    )}
                </p>
                <hr />
                <h3 className="text-center text-info">Skill Set</h3>
                <p>{profile.skills}</p>
            </div>
    )}

    return (
      <div className="row">
        <div className="col-md-12">
            {AboutContent}
        </div>
      </div>
    );
  }
}

ProfileAbout.propTypes = {
  profile: PropTypes.object.isRequired
};

export default ProfileAbout;
