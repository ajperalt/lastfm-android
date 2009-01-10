// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package fm.last.api.impl;

import java.util.List;

import org.w3c.dom.Node;

import fm.last.api.Artist;
import fm.last.api.ImageUrl;
import fm.last.api.Track;
import fm.last.api.Album;
import fm.last.xml.XMLBuilder;

/**
 * @author Mike Jennings
 */
public class TrackBuilder extends XMLBuilder<Track> {
  private ArtistBuilder artistBuilder = new ArtistBuilder();
//  private AlbumBuilder albumBuilder = new AlbumBuilder();
  private ImageUrlBuilder imageBuilder = new ImageUrlBuilder();

  public Track build(Node trackNode) {
    node = trackNode;
    String id = getText("id");
    String name = getText("name");
    String mbid = getText("mbid");
    String url = getText("url");
    String duration = getText("duration");
    String streamable = getText("streamable");
    String listeners = getText("listeners");
    String playcount = getText("playcount");
    Node artistNode = getChildNode("artist");
    
    List<Node> imageNodes = getChildNodes("image");
    ImageUrl[] images = new ImageUrl[imageNodes.size()];
    int i = 0;
    for (Node imageNode : imageNodes) {
      images[i++] =imageBuilder.build(imageNode);
    }
    
    Artist artist = artistBuilder.build(artistNode);
//    Node albumNode = getChildNode("album");
//    Album album = albumBuilder.build(albumNode);
    return new Track(id, name, mbid, url, duration, streamable, listeners, playcount, artist, null, images);
  }
}