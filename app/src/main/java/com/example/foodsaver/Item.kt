package com.example.foodsaver

import android.os.Parcel
import android.os.Parcelable

/**
 * This is a data class for Item Model/Entity.
 */
data class Item(
    val id: String,
    val productName: String,
    val image: String,
    val expireDate: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(productName)
        parcel.writeString(image)
        parcel.writeString(expireDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
