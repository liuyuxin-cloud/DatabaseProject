package com.example.databaseproject.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.databaseproject.daos.MemberDao;
import com.example.databaseproject.database.BookStoreDatabase;
import com.example.databaseproject.entities.Membership;

import java.util.List;

public class MemberRepository {
    private MemberDao memberDao;
    private LiveData<List<Membership>> memberships;

    public MemberRepository(Application application) {
        BookStoreDatabase db = BookStoreDatabase.getDatabase(application);
        memberDao = db.memberDao();
        memberships = memberDao.getAllMember();
    }

    public LiveData<List<Membership>> getMemberships() {
        return memberships;
    }

    public void insert(Membership mem) {
        new MemberRepository.insertAsyncTask(memberDao).execute(mem);
    }

    public void update(Membership mem) {
        new MemberRepository.updateAsyncTask(memberDao).execute(mem);
    }

    private static class insertAsyncTask extends AsyncTask<Membership, Void, Void> {
        private MemberDao mAsyncTaskDao;

        insertAsyncTask(MemberDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Membership... params) {
            mAsyncTaskDao.insertMember(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Membership, Void, Void> {
        private MemberDao mAsyncTaskDao;

        updateAsyncTask(MemberDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Membership... params) {
            mAsyncTaskDao.updateMember(params[0]);
            return null;
        }
    }
}
