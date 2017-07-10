#include <jni.h>
#include <string>

const std::string apiKey = "MOVIE-DB-API-KEY";
const std::string url = "http://api.themoviedb.org/3/";
const std::string urlImage = "http://image.tmdb.org/t/p/";

extern "C"
JNIEXPORT jstring JNICALL
Java_id_ihsan_popmovie_MovieApplication_apiKey(JNIEnv *env, jobject) {
    return env->NewStringUTF(apiKey.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_id_ihsan_popmovie_utils_Constans_url(JNIEnv *env, jobject) {
    return env->NewStringUTF(url.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_id_ihsan_popmovie_utils_Constans_urlImage(JNIEnv *env, jobject) {
    return env->NewStringUTF(urlImage.c_str());
}
