/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.user.internal.document;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.user.User;
import org.xwiki.user.UserReference;
import org.xwiki.user.UserResolver;

import com.xpn.xwiki.user.api.XWikiUser;

/**
 * Converts an oldcore {@link XWikiUser} into a {@link User}.
 *
 * @version $Id$
 * @since 12.2RC1
 */
@Component
@Named("com.xpn.xwiki.user.api.XWikiUser")
@Singleton
public class DocumentXWikiUserUserResolver extends AbstractDocumentUserResolver<XWikiUser>
{
    @Inject
    @Named("org.xwiki.user.CurrentUserReference")
    private UserResolver<UserReference> currentUserResolver;

    @Override
    public User resolve(XWikiUser xwikiUser, Object... parameters)
    {
        User user;

        if (xwikiUser == null) {
            user = this.currentUserResolver.resolve(null);
        } else {
            DocumentReference documentReference = xwikiUser.getUserReference();
            user = resolveUser(documentReference);
        }
        return user;
    }
}
