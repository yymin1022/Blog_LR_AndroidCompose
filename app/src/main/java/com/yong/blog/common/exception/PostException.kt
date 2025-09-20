package com.yong.blog.common.exception

import java.lang.Exception

class PostException(
    override val message: String = "Post Error"
): Exception()