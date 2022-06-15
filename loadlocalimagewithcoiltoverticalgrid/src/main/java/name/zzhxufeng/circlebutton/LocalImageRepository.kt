package name.zzhxufeng.circlebutton

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val IMAGES_MEDIA_URI: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

class LocalImageRepository {
    private val localImgAlbums = mutableListOf<LocalImgAlbumModel>()
    private val localImgs = mutableListOf<LocalImgModel>()
    private val contentResolver: ContentResolver = App.app.contentResolver

    suspend fun loadAllImgs(): List<LocalImgModel>
    = withContext(Dispatchers.IO) {
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATA,

            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        contentResolver.query(
            IMAGES_MEDIA_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val bucketIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val bucketNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            val imageIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val imageNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val imageDateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

            var bucketId: Long
            var bucketName: String
            var bucketRelativePath: String
            var imageId: Long
            var imageName: String
            var imageDateTaken: Long

            while (cursor.moveToNext()){
                bucketId = cursor.getLong(bucketIdColumn)
                bucketName = cursor.getString(bucketNameColumn)
                imageId = cursor.getLong(imageIdColumn)
                imageName = cursor.getString(imageNameColumn)
                imageDateTaken = cursor.getLong(imageDateTakenColumn)

                if(localImgAlbums.filter { it.id == bucketId }.isEmpty()){
                    // Add new album here
                    localImgAlbums.add(
                        LocalImgAlbumModel(
                            bucketId,
                            bucketName,
                            ContentUris.withAppendedId(
                                IMAGES_MEDIA_URI,
                                imageId
                            )
                        )
                    )
                }

                localImgs.add(
                    LocalImgModel(
                        imageId,
                        imageName,
                        localImgAlbums.filter { it.id == bucketId }[0].id,
                        ContentUris.withAppendedId(
                            IMAGES_MEDIA_URI,
                            imageId
                        ),
                        ContentUris.withAppendedId(
                            IMAGES_MEDIA_URI,
                            imageId
                        ),
                        imageDateTaken
                    )
                )
            }
        }
        localImgs
    }
}

data class LocalImgModel(
    val id: Long,
    val name: String,
    val albumId: Long,
    val thumbnailUri: Uri,
    val uri: Uri,
    val createdTimestamp: Long
)

data class LocalImgAlbumModel(
    val id: Long,
    val name: String,
    val thumbnailUri: Uri,
    var itemsCount: Int = 0
)
