package com.angoga.roomlessson_23_24.callbacks;

import com.angoga.roomlessson_23_24.database.entity.Publication;

public interface OnPublicationClicked {
    public void onLikeClicked(Publication publication);
    public void onRemoveClicked(Publication publication);
}
