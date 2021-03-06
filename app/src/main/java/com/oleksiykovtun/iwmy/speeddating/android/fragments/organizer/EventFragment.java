package com.oleksiykovtun.iwmy.speeddating.android.fragments.organizer;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.oleksiykovtun.android.cooltools.CoolFragmentManager;
import com.oleksiykovtun.iwmy.speeddating.R;
import com.oleksiykovtun.iwmy.speeddating.android.ImageManager;
import com.oleksiykovtun.iwmy.speeddating.android.fragments.AppFragment;
import com.oleksiykovtun.iwmy.speeddating.data.Event;

/**
 * Created by alx on 2015-02-12.
 */
public class EventFragment extends AppFragment {

    private Event event = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organizer_event, container, false);
        registerContainerView(view);
        registerClickListener(R.id.button_options);
        registerClickListener(R.id.button_edit_start);
        registerClickListener(R.id.button_participants_list);
        registerClickListener(R.id.button_settings);

        event = (Event) getAttachment();

        ImageManager.setEventPhoto(getImageView(R.id.photo), event.getPhoto());
        setText(R.id.label_organizer, event.getPlace());
        setText(R.id.label_event_address,
                R.string.label_event_address, event.getFullStreetAddress());
        setText(R.id.label_event_cost,
                R.string.label_event_cost, event.getCost());
        setText(R.id.label_event_description,
                R.string.label_event_description, event.getDescription());
        setText(R.id.label_event_organizer_and_place,
                R.string.label_event_organizer_and_place,
                event.getOrganizerEmail() + ", " + event.getPlace());
        setText(R.id.label_event_time,
                R.string.label_event_time, event.getTime());
        setText(R.id.label_event_places,
                R.string.label_event_places, event.getFreePlaces());
        setText(R.id.label_event_min_allowed_age,
                R.string.label_event_min_allowed_age, event.getMinAllowedAge());
        setText(R.id.label_event_max_allowed_age,
                R.string.label_event_max_allowed_age, event.getMaxAllowedAge());

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_options:
                openMenu(view);
                break;
            case R.id.button_edit_start:
                CoolFragmentManager.showAtTop(new QuestionnaireListFragment(), event);
                break;
            case R.id.button_participants_list:
                CoolFragmentManager.showAtTop(new ParticipantListFragment(), event);
                break;
            case R.id.button_settings:
                CoolFragmentManager.showAtTop(new SettingsFragment());
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CoolFragmentManager.show(new EventEditFragment(), event);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.event, menu);
    }

}
