package com.github.andreyasadchy.xtra.ui.view.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.github.andreyasadchy.xtra.UserQuery
import com.github.andreyasadchy.xtra.di.XtraModule
import com.github.andreyasadchy.xtra.di.XtraModule_ApolloClientFactory.apolloClient
import com.github.andreyasadchy.xtra.model.helix.user.User
import com.github.andreyasadchy.xtra.repository.TwitchService
import com.github.andreyasadchy.xtra.ui.common.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageClickedViewModel @Inject constructor(private val repository: TwitchService) : BaseViewModel() {

    private val user = MutableLiveData<User>()
    private var isLoading = false

    fun loadUser(clientId: String?, token: String?, channelId: String): LiveData<User> {
        if (user.value == null && !isLoading) {
            isLoading = true
            viewModelScope.launch {
                try {
                    val u = repository.loadUserById(clientId, token, channelId)
                    user.postValue(u)
                } catch (e: Exception) {
                    _errors.postValue(e)
                } finally {
                    isLoading = false
                }
            }
        }
        return user
    }

    fun loadUserGQL(clientId: String?, channelId: String): LiveData<User> {
        if (user.value == null && !isLoading) {
            isLoading = true
            viewModelScope.launch {
                try {
                    val get = apolloClient(XtraModule(), clientId).query(UserQuery(id = Optional.Present(channelId))).execute().data?.user
                    val u = User(
                        id = get?.id,
                        login = get?.login,
                        display_name = get?.displayName,
                        profile_image_url = get?.profileImageURL,
                    )
                    user.postValue(u)
                } catch (e: Exception) {
                    _errors.postValue(e)
                } finally {
                    isLoading = false
                }
            }
        }
        return user
    }
}