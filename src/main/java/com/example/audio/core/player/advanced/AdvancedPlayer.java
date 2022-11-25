/*
 * 11/19/04        1.0 moved to LGPL.
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */

package com.example.audio.core.player.advanced;

import com.example.audio.core.decoder.*;
import com.example.audio.core.player.AudioDevice;
import com.example.audio.core.player.FactoryRegistry;

import java.io.InputStream;


/**
 * a hybrid of javazoom.jl.player.Player tweeked to include <code>play(startFrame, endFrame)</code>
 * hopefully this will be included in the api
 */
public class AdvancedPlayer {

    /** The MPEG audio bitstream. */
    private final Bitstream bitstream;
    /** The AudioDevice the audio samples are written to. */
    private AudioDevice audio;

    public AdvancedPlayer(InputStream stream, AudioDevice device) throws JavaLayerException {
        bitstream = new Bitstream(stream);

        if (device != null) audio = device;
        else audio = FactoryRegistry.systemRegistry().createAudioDevice();
        audio.open(new Decoder());
    }

    /**
     * Cloases this player. Any audio currently playing is stopped
     * immediately.
     */
    public synchronized void close() {
        AudioDevice out = audio;
        if (out != null) {
            audio = null;
            // this may fail, so ensure object state is set up before
            // calling this method.
            out.close();
            try {
                bitstream.close();
            } catch (BitstreamException ex) {
                ex.printStackTrace();
            }
        }
    }

}