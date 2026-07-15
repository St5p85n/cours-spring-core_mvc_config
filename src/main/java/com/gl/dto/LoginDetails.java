package com.gl.dto;


import com.gl.model.Role;

import java.util.List;
import java.util.Set;

public record LoginDetails(String username, String token, List<String> roles) { }
