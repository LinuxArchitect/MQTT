package me.cowprotector.MQTT.commands;
// register all commands at runtime, credit to Jordan Osterberg https://www.patreon.com/jordanosterberg

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
  String name();
  String permission() default "";
  boolean requiresPlayer();
}
