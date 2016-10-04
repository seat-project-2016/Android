package com.seat.Pages.Registration.Interfaces;

/**
 * Created by Devrath on 10-09-2016.
 */
public interface IntRegistrationView {
    void registrationSuccess();
    void registrationFailure();
    void validationEmailFailure();
    void validationPasswordFailure();
    void validationPasswordSuccess();
}
