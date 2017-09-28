package com.example.vinh.simplemvvm;

import android.databinding.ObservableArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserListViewModelTest {
    private UserListViewModel userListViewModel;

    @Mock
    private UserListViewModel.OnCreateUserListener onCreateUserListener;

    @Mock
    private UserListViewModel.OnItemClickListener onItemClickListener;

    @Mock
    private UserProvider userProvider;

    private ObservableArrayList<UserViewModel> userViewModels = new ObservableArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userListViewModel = new UserListViewModel(userProvider,
                new ObservableArrayList<UserViewModel>());
    }

    @Test
    public void testCreateUser_createActualUser() {
        userListViewModel.createUser();
        Assert.assertNotNull(userListViewModel.getUserViewModel(0));
    }

    @Test
    public void testCreateUser_createCorrectUser() {
        User user = new User("User 0");

        when(userProvider.createUser(0)).thenReturn(user);

        userListViewModel.createUser();

        User actualUser = userListViewModel.getUserViewModel(0).getUser();
        Assert.assertEquals(user, actualUser);
    }

    @Test
    public void testCreateUser_createCorrectUserWithEmitEvent() {
        User user = new User("User 0");

        when(userProvider.createUser(0)).thenReturn(user);

        userListViewModel.setOnItemClickListener(onItemClickListener);
        userListViewModel.setOnCreateUserListener(onCreateUserListener);
        userListViewModel.createUser();

        User actualUser = userListViewModel.getUserViewModel(0).getUser();
        Assert.assertNotNull(userListViewModel.getUserViewModel(0));
        Assert.assertEquals(user, actualUser);
        verify(onCreateUserListener, times(1)).onUserCreated(0, user);
    }

    @Test
    public void testSetOnItemClickListener_verifyCorrectSetOnClickListenrCalled() {
        userViewModels.add(mock(UserViewModel.class));
        userViewModels.add(mock(UserViewModel.class));
        userViewModels.add(mock(UserViewModel.class));
        userViewModels.add(mock(UserViewModel.class));

        userListViewModel.setOnItemClickListener(onItemClickListener);
        for (UserViewModel userViewModel : userListViewModel.getUserViewModels()) {
            verify(userViewModel, times(1)).setOnClickListener(userListViewModel);
        }
    }
}