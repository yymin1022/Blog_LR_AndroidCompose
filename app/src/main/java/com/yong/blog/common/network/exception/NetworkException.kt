package com.yong.blog.common.network.exception

import java.io.IOException

class NetworkException(
    override val message: String = "Network Unavailable"
): IOException(message)