package com.example.medilyf.report;

import com.example.medilyf.BasePresenter;
import com.example.medilyf.BaseView;
import com.example.medilyf.source.History;

import java.util.List;

/**
 * Created by gautam on 13/07/17.
 */

public interface MonthlyReportContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showHistoryList(List<History> historyList);

        void showLoadingError();

        void showNoHistory();

        void showTakenFilterLabel();

        void showIgnoredFilterLabel();

        void showAllFilterLabel();

        void showNoTakenHistory();

        void showNoIgnoredHistory();

        boolean isActive();

        void showFilteringPopUpMenu();

    }

    interface Presenter extends BasePresenter {

        void loadHistory(boolean showLoading);

        void setFiltering(com.example.medilyf.report.FilterType filterType);

        com.example.medilyf.report.FilterType getFilterType();
    }
}
