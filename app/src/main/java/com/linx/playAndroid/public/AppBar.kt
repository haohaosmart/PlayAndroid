package com.linx.playAndroid.public

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState

/**
 * 标题栏
 */
@Composable
fun AppBar(
    title: String = "",
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {}
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(54.dp),
        elevation = elevation,
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                //左边图标
                if (leftIcon == null)
                    Spacer(modifier = Modifier.size(10.dp))
                else
                    Icon(leftIcon, contentDescription = null, modifier = Modifier.clickable {
                        onLeftClick()
                    }.padding(start = 10.dp), tint = MaterialTheme.colors.background)

                //右边图标
                rightIcon?.let {
                    Icon(rightIcon, contentDescription = null, Modifier.clickable {
                        onRightClick()
                    }.padding(end = 10.dp), tint = MaterialTheme.colors.background)
                }
            }

            //标题文字
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = MaterialTheme.colors.background
            )
        }
    }
}

/**
 * 带动画的标题栏
 */
@Composable
fun AppBar(
    title: String = "",
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    leftIcon: ImageVector? = null,
    rightLottie: LottieComposition? = null,
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit,
) {

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(54.dp),
        elevation = elevation,
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //左边图标
                if (leftIcon == null)
                    Spacer(modifier = Modifier.size(10.dp))
                else
                    Icon(leftIcon, contentDescription = null, modifier = Modifier.clickable {
                        onLeftClick()
                    }.padding(start = 10.dp), tint = MaterialTheme.colors.background)

                //右边动画
                rightLottie?.let {
                    val lottieAnimationState by animateLottieCompositionAsState(
                        //动画资源句柄
                        composition = rightLottie,
                        //迭代次数
                        iterations = LottieConstants.IterateForever,
                        //动画播放状态
                        isPlaying = true,
                        //动画速度状态
                        speed = 1f,
                        //暂停后重新播放动画是否重新播放
                        restartOnPlay = true
                    )

                    LottieAnimation(
                        rightLottie,
                        lottieAnimationState,
                        modifier = Modifier.clickable(
                            onClick = onRightClick,
                            //波纹效果变淡 todo
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = MaterialTheme.colors.primary)
                        ).size(60.dp)
                    )
                }
            }

            //标题文字
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = MaterialTheme.colors.background
            )
        }
    }
}