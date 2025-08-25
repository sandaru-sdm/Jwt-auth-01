package com.sdm.jwt_auth_01.service;

import com.sdm.jwt_auth_01.io.ProfileRequest;
import com.sdm.jwt_auth_01.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);

    ProfileResponse getProfile(String email);

    void sendResetOtp(String email);

    void resetPassword(String newPassword, String otp, String email1);

    void sendOtp(String email);

    void verifyOtp(String email, String otp);
}
